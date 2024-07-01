import React from "react";
import { Link } from "react-router-dom";
import "./NavBar.css"; // Importuje se NavBar.css koji se nalazi u istom folderu

function NavBar() {
  return (
    <nav className="navbar">
      <ul className="navbar-list">
        <li className="navbar-item">
          <span className="navbar-link">Product</span>
          <ul className="submenu">
            <li className="submenu-item">
              <Link to="/AddProduct" className="navbar-link">Add product</Link>
            </li>
            <li className="submenu-item">
              <Link to="/ProductView" className="navbar-link">View all products</Link>
            </li>
          </ul>
        </li>
        <li className="navbar-item">
          <span className="navbar-link">Inventory</span>
          <ul className="submenu">
            <li className="submenu-item">
              <Link to="/AddInventory" className="navbar-link">Add inventory</Link>
            </li>
            <li className="submenu-item">
              <Link to="/ViewInventories" className="navbar-link">View all inventories</Link>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
  );
}

export default NavBar;
