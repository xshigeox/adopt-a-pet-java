import React, {useEffect, useState} from "react"
import TypeOfPet from "./TypeOfPet"

const TypeOfPetsContainer = props => {
  const [petType, setPetType] = useState([])

  useEffect(() => {
    fetch("/api/v1/pets")
    .then(response => {
      if (response.ok) {
        return response
      } else {
        let errorMessage = `${response.status} (${response.statusText})`
        throw new Error(errorMessage)
      }
    })
    .then(result => {
      return result.json()
    })
    .then(json => {
      setPetType(json)
    })
    .catch(error => {
      console.log(error)
    })
  }, [])

  const listOfPets = petType.map(element => {
    return <TypeOfPet data={element} key={element.name}/>
  })

  return (
      <div>
        <div className="featured-image-block-grid">
          <div className="featured-image-block-grid-header columns text-center">
            <h2 id="hero-section-text">Pet Types</h2>
            <p>Select your future best friend.</p>
          </div>
          <div className="row">{listOfPets}</div>
        </div>
      </div>
  )
}
export default TypeOfPetsContainer
