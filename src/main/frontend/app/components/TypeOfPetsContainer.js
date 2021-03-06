import React, { useEffect, useState } from "react"
import TypeOfPet from "./TypeOfPet"

const TypeOfPetsContainer = (props) => {
  const [petType, setPetType] = useState([])

  useEffect(() => {
    fetch("/api/v1/pets")
      .then((response) => {
        if (response.ok) {
          return response
        } else {
          let errorMessage = `${response.status} (${response.statusText})`
          throw new Error(errorMessage)
        }
      })
      .then((result) => {
        return result.json()
      })
      .then((json) => {
        setPetType(json)
      })
      .catch((error) => {
        console.log(error)
      })
  }, [])

  const listOfPets = petType.map((element) => {
    let typeName
    if (element.type === "reptile") {
      typeName = "Reptiles"
    } else if (element.type === "guineapig") {
      typeName = "Guinea Pigs"
    }
    return <TypeOfPet key={element.type} data={element} type={typeName} />
  })

  return (
    <div>
      <div className="featured-image-block-grid">
        <div className="featured-image-block-grid-header columns text-center">
          <h2 id="hero-section-text">Find Your Future Best Friend</h2>
          <p>Select a type of pet</p>
        </div>
        <div className="row">{listOfPets}</div>
      </div>
    </div>
  )
}
export default TypeOfPetsContainer
