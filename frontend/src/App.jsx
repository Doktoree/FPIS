import { useState, useEffect } from "react";
import "./App.css";
import ProductView from "./components/product/ProductView";
import * as productService from "./services/productService";
import * as inventoryService from "./services/inventoryService";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/navbar/NavBar";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import AddProduct from "./components/product/AddProduct";
import UpdateProduct from "./components/product/UpdateProduct";
import InventoryView from "./components/inventory/InventoryView";
import AddInventory from "./components/inventory/AddInventory";
import Login from "./components/login/Login";
import UserDetails from "./components/user/UserDetails";

function App() {
  const [products, setProducts] = useState([]);
  const [inventories, setInvetories] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loggedInUser, setLoggedInUser] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const products = await productService.getAllProducts();
        console.log(products);
        setProducts(products);
        const inventories = await inventoryService.getAllInventories();
        setInvetories(inventories);
        console.log(inventories);
      } catch (error) {
        console.log("Greska!");
      }
    }

    fetchData();
  }, []);

  return (
    <>
      <Router>
        <div>
          {isLoggedIn && <NavBar />}
          <Routes>
            <Route path="/" element = {<Login setIsLoggedIn={setIsLoggedIn} setLoggedInUser = {setLoggedInUser}/> }/>
            <Route path="/UserDetails" element = {isLoggedIn?<UserDetails loggedInUser={loggedInUser} setIsLoggedIn={setIsLoggedIn} setLoggedInUser={setLoggedInUser}/>: <Navigate to = "/" />} />
            <Route path="/ProductView" element={isLoggedIn?<ProductView products={products} />: <Navigate to="/"/>} />
            <Route path="/AddProduct" element = {isLoggedIn?<AddProduct/>:<Navigate to = "/"/>}/>
            <Route path="/UpdateProduct" element={isLoggedIn?<UpdateProduct />:<Navigate to= "/"/>} />
            <Route path="/InventoryView" element={isLoggedIn?<InventoryView inventories={inventories}/> : <Navigate to = "/"/>} />
            <Route path="/AddInventory" element={isLoggedIn?<AddInventory loggedInUser={loggedInUser}/>:<Navigate to = "/" />} />
          </Routes>
        </div>
      </Router>
    </>
  );
}

export default App;
