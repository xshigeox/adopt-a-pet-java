import React from "react"
import { BrowserRouter, Switch, Link, Route, Redirect } from "react-router-dom"
import TypeOfPetsContainer from "./components/TypeOfPetsContainer"
import PetListContainer from "./components/PetListContainer"
import PetShowContainer from "./components/PetShowContainer"
import SurrenderForm from "./components/SurrenderForm"
import LoginPage from "./components/LoginPage"
import EditApplicationContainer from "./components/EditApplicationContainer"
import AdoptedPetContainer from "./components/AdoptedPetContainer"

const App = (props) => {
  return (
    <BrowserRouter>
      <div className="top-links">
        <nav className="top-bar topbar-responsive">
          <div className="top-bar-title">
            <Link to="/" id="topbar-site-title">
              <strong>Pet Adoption Site</strong>
            </Link>
          </div>
          <div id="topbar-responsive" className="topbar-responsive-links">
            <div className="top-bar-right">
              <ul className="menu simple vertical medium-horizontal">
                <li>
                  <Link to="/">Home</Link>
                </li>
                <li>
                  <Link to="/pets/guineapigs">Guinea Pigs</Link>
                </li>
                <li>
                  <Link to="/pets/reptiles">Reptiles</Link>
                </li>
                <li>
                  <Link to="/adopted">Adopted Pets</Link>
                </li>
                <li>
                  <Link to="/adoptions/new">Put up for Adoption</Link>
                </li>

                <li className="admin-button">
                  <Link to="/login">Login</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>

      <div className="bottom">
        <Switch>
          <Redirect exact path="/" to="/pets" />
          <Route exact path="/pets" component={TypeOfPetsContainer} />
          <Route exact path="/pets/guineapigs" key={"gp"}>
            <PetListContainer
              petType={"guineapig"}
              petTypeName={"Guinea Pigs"}
            />
          </Route>
          <Route exact path="/pets/reptiles" key={"lz"}>
            <PetListContainer petType={"reptile"} petTypeName={"Reptiles"} />
          </Route>
          <Route exact path="/adoptions/new" component={SurrenderForm} />
          <Route exact path="/login" component={LoginPage} />
          <Route exact path="/pets/:petType/:id" component={PetShowContainer} />
          <Route
            exact
            path="/pending_applications"
            component={EditApplicationContainer}
          />
          <Route exact path="/adopted" component={AdoptedPetContainer} />
        </Switch>
      </div>
    </BrowserRouter>
  )
}
export default App
