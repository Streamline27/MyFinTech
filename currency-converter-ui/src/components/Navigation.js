import React from "react";
import logo from "../img/MyFinTech2.png";

export function Navigation(props) {
    const { onLogout } = props

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <a className="navbar-brand" href="/#">MyFinTech</a>
            <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div className="navbar-nav">
                    <a className="nav-link active" href="/#">Currency exchange</a>
                    <a className="nav-link disabled" href="/#">Role dashboard</a>
                    <a className="nav-link disabled" href="/#">User profile</a>
                </div>
            </div>
            <form className="form-inline my-2 my-lg-0">
                <button className="btn btn-outline-dark my-2 my-sm-0" type="button" onClick={onLogout}><i className="fa fa-sign-out"/> Sign out</button>
            </form>
        </nav>
    )
}
