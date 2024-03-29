import React from "react"
import { Link } from "react-router-dom"

const TypeOfPet = (props) => {
  const { type, description } = props.data
  const path = "/pets/" + type + "s"

  return (
    <div className="featured-image-block medium-6 column">
      <Link to={path}>
        <img className="resizing-img" src={`images/${type}.jpg`} alt={type} />
        <p className="text-center featured-image-block-title">{props.type}</p>
      </Link>
      <p>{description}</p>
    </div>
  )
}

export default TypeOfPet
