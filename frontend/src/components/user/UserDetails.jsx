import React from "react";
import { useNavigate } from "react-router-dom";

function UserDetails({ loggedInUser, setLoggedInUser, setIsLoggedIn }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    setLoggedInUser(null);
    setIsLoggedIn(false);
    localStorage.removeItem("user");
    navigate("/");
  };

  return (
    <div className="container mt-4">
      <h2>User Details</h2>
      <form>
        <div className="form-group">
          <label htmlFor="employeeId">Employee ID:</label>
          <input
            type="text"
            className="form-control"
            id="employeeId"
            value={loggedInUser.employee.employeeId}
            readOnly
          />
        </div>
        <div className="form-group">
          <label htmlFor="firstName">First Name:</label>
          <input
            type="text"
            className="form-control"
            id="firstName"
            value={loggedInUser.employee.firstName}
            readOnly
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name:</label>
          <input
            type="text"
            className="form-control"
            id="lastName"
            value={loggedInUser.employee.lastName}
            readOnly
          />
        </div>
        <div className="form-group">
          <label htmlFor="department">Department:</label>
          <input
            type="text"
            className="form-control"
            id="department"
            value={loggedInUser.employee.department.name}
            readOnly
          />
        </div>
        <button
          type="button"
          className="btn btn-primary"
          onClick={handleLogout}
        >
          Logout
        </button>
      </form>
    </div>
  );
}

export default UserDetails;
