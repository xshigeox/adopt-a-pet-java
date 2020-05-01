import React, { useState } from "react"
import { Link } from "react-router-dom"

const PendingSurrenderList = (props) => {
  const [story, setStory] = useState({
    story: "",
  })
  const {
    id,
    name,
    phoneNumber,
    email,
    petName,
    petAge,
    petType,
    petImgUrl,
    vaccinationStatus,
    applicationStatus,
  } = props.data

  let status
  if (vaccinationStatus === true) {
    status = "Up to Date"
  } else {
    status = "Not Up to Date"
  }

  let animalType
  if (petType.type === "reptile") {
    animalType = "Reptile"
  } else if (petType.type === "guineapig") {
    animalType = "Guinea Pig"
  }

  const handleInputChange = (event) => {
    setStory({
      ...story,
      [event.currentTarget.id]: event.currentTarget.value,
    })
  }

  const updateStatus = (event) => {
    event.preventDefault()
    const approvalStatus = {
      name: petName,
      img_url: petImgUrl,
      age: petAge,
      vaccinationStatus: vaccinationStatus,
      adoptionStory: story.story,
      adoptionStatus: "Pending",
      petType: petType,
      applicationStatus: "Approved",
    }

    fetch("/api/v1/surrender_status", {
      method: "POST",
      body: JSON.stringify(approvalStatus),
      headers: { "Content-Type": "application/json" },
    })
      .then((response) => {
        if (response.ok) {
          return response
        } else {
          let errorMessage = `${response.statues} (${response.statusText})`,
            error = new Error(errorMessage)
          throw error
        }
      })
      .catch((error) => console.error(`Error in fetch: ${error.message}`))
    alert("Form " + event.currentTarget.value)
    window.location.href = "http://localhost:8080"
  }

  return (
    <div className="add-pets-section">
      <div className="row ">
        <div className="small-6 columns about-pets-avatar">
          <img
            className="avatar-image pending-form-img"
            src={petImgUrl}
            alt={petName}
          />
        </div>

        <div className="small-6 columns about-pets div-pending-pet-applicant">
          <div className="about-pets-author">
            <p className="author-name">{petName}</p>
            <p className="author-location">Type of Animal: {animalType}</p>
            <p className="author-location">Vaccination Status: {status}</p>
            <p className="author-location">Pet Age: {petAge}</p>
            <p className="author-location">
              Application Status: {applicationStatus}
            </p>
          </div>
        </div>

        <div className="small-6 columns about-pets div-pending-pet-applicant">
          <div className="about-pets-author">
            <p className="author-name">Applicant: {name}</p>
            <p className="author-location">Phone Number: {phoneNumber}</p>
            <p className="author-location">Email: {email}</p>
          </div>
        </div>
        <div className="small-6 columns add-friend div-pending-button">
          <div className="add-friend-action">
            <Link
              to={{
                pathname: "/pending_applications",
                editProps: { applicant: props.data },
              }}
            >
              <button className="button primary small" value="Approved" id={id}>
                Edit Application
              </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  )
}

export default PendingSurrenderList
