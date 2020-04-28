import React, { useState, useEffect } from "react"
import PetInfo from "./PetInfo"

const ShowPage = (props) => {
  const [pet, setPet] = useState({})
  const petId = props.match.params.id
  const petType = props.match.params.petType

  useEffect(() => {
    fetch(`/api/v1/${petType}/${petId}`)
      .then((response) => {
        if (response.ok) {
          return response
        } else {
          let errorMessage = `${response.status} (${response.statusText})`,
            error = new Error(errorMessage)
          throw error
        }
      })
      .then((response) => response.json())
      .then((fetchedPet) => {
        setPet(fetchedPet)
      })
  }, {})

  console.log(pet)
  return <PetInfo key={petId} pet={pet} />
}

export default ShowPage
