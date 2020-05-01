import React from "react"

const PendingAppList = (props) => {
  const {
    imgUrl,
    vaccinationStatus,
    adoptionStory,
    name,
    phoneNumber,
    email,
    homeStatus,
    applicationStatus,
    id,
    pet,
  } = props.data

  let status
  if (vaccinationStatus === true) {
    status = "Up to Date"
  } else {
    status = "Not Up to Date"
  }

  const updateStatus = (event) => {
    event.preventDefault()
    const approvalStatus = {
      status: event.currentTarget.value,
      id: event.currentTarget.id,
    }

    fetch("/api/v1/approval_status", {
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

  console.log(pet)

  return (
    <div className="add-pets-section">
      <div className="row ">
        <div className="small-6 columns about-pets-avatar">
          <img
            className="avatar-image pending-form-img"
            src={pet.imgUrl}
            alt={pet.name}
          />
        </div>

        <div className="small-6 columns about-pets div-pending-pet-applicant">
          <div className="about-pets-author">
            <p className="author-name">{pet.name}</p>
            <p className="author-location">Vaccination Status: {status}</p>
            <p className="author-location">Adoption Story: {adoptionStory}</p>
            <p className="author-location">
              Adoption Status: {applicationStatus}
            </p>
          </div>
        </div>

        <div className="small-6 columns about-pets div-pending-pet-applicant">
          <div className="about-pets-author">
            <p className="author-name">Applicant: {name}</p>
            <p className="author-location">Phone Number: {phoneNumber}</p>
            <p className="author-location">Email: {email}</p>
            <p className="author-location">Home Status: {homeStatus}</p>
            <p className="author-location">
              Application Status: {applicationStatus}
            </p>
          </div>
        </div>
        <div className="small-6 columns add-friend div-pending-button">
          <div className="add-friend-action">
            <button
              className="button primary small"
              value="Approved"
              id={id}
              onClick={updateStatus}
            >
              <i className="far fa-smile" aria-hidden="true"></i> Approve
            </button>
            <button
              className="button secondary small"
              value="Denied"
              id={id}
              onClick={updateStatus}
            >
              <i className="far fa-frown" aria-hidden="true"></i> Deny
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}
export default PendingAppList
