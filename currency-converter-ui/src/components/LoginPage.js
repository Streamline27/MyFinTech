import React from "react";
import logo from "../img/MyFinTech2.png";

export function LoginPage(props) {
    const { onLoggedIn } = props

    function handleGoogleLoginClick() {
        onLoggedIn()
    }

    return (
        <main className="signin-container text-center">
            <div className="form-signin">
                <form>
                    <img className="mb-4" src={logo} alt=""/>
                    <button className="w-100 btn btn-lg btn-outline-dark" type="submit" onClick={handleGoogleLoginClick}>
                        <img width="20px" className="img-google" alt="Google sign-in" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png"/>
                        Continue with Google
                    </button>
                    <p>or</p>
                    <input type="email" className="form-control" placeholder="Email address" autoFocus/>
                    <input type="password" className="form-control" placeholder="Password"/>
                    <div className="checkbox mb-3">
                        <label>
                            <input type="checkbox" value="remember-me"/> Remember me
                        </label>
                    </div>
                    <button className="w-100 btn btn-lg btn-secondary" type="submit">Log in</button>
                    <p className="mt-5 mb-3 text-muted">© 2020-2025</p>
                </form>
            </div>
        </main>
    );
}