import { useState, useEffect } from "react";
import "./App.css";
import ProductView from "./components/product/ProductView";
import * as inventoryService from "./services/inventoryService";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/navbar/NavBar";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import AddProduct from "./components/product/AddProduct";
import UpdateProduct from "./components/product/UpdateProduct";
import InventoryView from "./components/inventory/InventoryView";
import AddInventory from "./components/inventory/AddInventory";
import Login from "./components/login/Login";
import UserDetails from "./components/user/UserDetails";
import InventoryDetails from "./components/inventory/InventoryDetails";
import AddInventoryItem from "./components/inventory/AddInventoryItem";

function App() {
  const [inventories, setInvetories] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loggedInUser, setLoggedInUser] = useState(null);
  const [trigger, setTrigger] = useState(0);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user) {
      setIsLoggedIn(true);
      setLoggedInUser(user);
    }

    async function fetchData() {
      try {
        const inventories = await inventoryService.getAllInventories();
        setInvetories(inventories);
      } catch (error) {
        console.log("Greska!");
      }
    }

    fetchData();
  }, [trigger]);

  return (
    <>
      <Router>
        <div>
          {isLoggedIn && <NavBar />}
          <Routes>
            {!isLoggedIn && (
              <Route
                path="/"
                element={
                  <Login
                    setIsLoggedIn={setIsLoggedIn}
                    setLoggedInUser={setLoggedInUser}
                  />
                }
              />
            )}
            <Route
              path="/UserDetails"
              element={
                isLoggedIn ? (
                  <UserDetails
                    loggedInUser={loggedInUser}
                    setIsLoggedIn={setIsLoggedIn}
                    setLoggedInUser={setLoggedInUser}
                  />
                ) : (
                  <Navigate to="/" />
                )
              }
            />
            <Route
              path="/ProductView"
              element={isLoggedIn ? <ProductView /> : <Navigate to="/" />}
            />
            <Route
              path="/AddProduct"
              element={
                isLoggedIn ? (
                  <AddProduct setTrigger={setTrigger} />
                ) : (
                  <Navigate to="/" />
                )
              }
            />
            <Route
              path="/UpdateProduct"
              element={isLoggedIn ? <UpdateProduct /> : <Navigate to="/" />}
            />
            <Route
              path="/InventoryView"
              element={
                isLoggedIn ? (
                  <InventoryView inventories={inventories} />
                ) : (
                  <Navigate to="/" />
                )
              }
            />
            <Route
              path="/AddInventory"
              element={
                isLoggedIn ? (
                  <AddInventory loggedInUser={loggedInUser} />
                ) : (
                  <Navigate to="/" />
                )
              }
            />
            <Route
              path="/InventoryDetails"
              element={isLoggedIn ? <InventoryDetails /> : <Navigate to="/" />}
            />
            <Route
              path="/AddInventoryItem"
              element={
                isLoggedIn ? (
                  <AddInventoryItem loggedInUser={loggedInUser} />
                ) : (
                  <Navigate to="/" />
                )
              }
            />
          </Routes>
        </div>
      </Router>
    </>
  );
}

export default App;
