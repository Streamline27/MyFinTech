import React, {useState} from "react";
import {HomePage} from "./components/HomePage";
import {LoginPage} from "./components/LoginPage";

export const API_HOST = window.RUNTIME_API_HOST ? window.RUNTIME_API_HOST : "http://localhost:3000";

function App() {
    const [ isLoggedIn, setIsLoggedIn ] = useState(false)

    function handleLoggedIn() {
        setIsLoggedIn(true)
    }

    function handleLogout() {
        setIsLoggedIn(false)
    }

    return (
        <div>
            { isLoggedIn  && <HomePage onLogout={handleLogout}/>}
            { !isLoggedIn && <LoginPage onLoggedIn={handleLoggedIn}/> }
        </div>
    );
}

export default App;
