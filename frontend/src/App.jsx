import { useState, useEffect } from "react";
import "./App.css";
import ProductView from "./components/product/ProductView";
import * as productService from "./services/productService";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/navbar/NavBar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddProduct from "./components/product/AddProduct";
import UpdateProduct from "./components/product/UpdateProduct";

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const products = await productService.getAllProducts();
        console.log(products);
        setProducts(products);
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
          <NavBar />
          <Routes>
            <Route path="/ProductView" element={<ProductView products={products} />} />
            <Route path="/AddProduct" element = {<AddProduct/>}/>
            <Route path="/UpdateProduct" element={<UpdateProduct />} />
          </Routes>
        </div>
      </Router>
    </>
  );
}

export default App;
