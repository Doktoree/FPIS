import React, { useState } from "react";
import { checkUser } from "../../services/userService";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login({ setIsLoggedIn, setLoggedInUser }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    try {
      event.preventDefault();
      const user = {
        username: username,
        password: password,
      };

      const response = await checkUser(user);
      setLoggedInUser(response);
      setIsLoggedIn(true);
      localStorage.setItem("user", JSON.stringify(response));
      navigate("/NavBar");
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleLogin}>
        <h2>Login</h2>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            className="form-control"
            id="username"
            required
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            className="form-control"
            id="password"
            required
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Login
        </button>
      </form>
    </div>
  );
}

export default Login;
