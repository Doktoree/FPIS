import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  deleteProduct,
  getAllProducts,
  getProductByExample,
  getProductById,
} from "../../services/productService";
import { useNavigate } from "react-router-dom";
import "./ProductView.css";

function ProductView({ products }) {
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [filteredProduct, setFilteredProduct] = useState(products);
  const[selectedProduct, setSelectedProduct] = useState(null);
  const navigate = useNavigate();

  const searchHandler = async () => {
    try {
      let results;

      const searchProduct = {
        productId: id,
        name: name,
      };

      if (id.trim() != "") {
        results = await getProductById(id);
        results = [results];
      } else if (id.trim() == "" && name.trim() == "") {
        results = await getAllProducts();
      } else {
        results = await getProductByExample(searchProduct);
      }

      setFilteredProduct(results);
    } catch (error) {
      alert(error.message);
    }
  };

  const selectHandler = (product) =>{

    setSelectedProduct(product);

  }

  const deleteHandler = async () => {

    if(selectedProduct == null){
      alert('There is no selected product!');
      return;
    }

    const id = selectedProduct.productId;
    const message = await deleteProduct(id);
    alert(message);
  }

  const updateHandler = () => {
    if (selectedProduct == null) {
      alert("There is no selected product!");
      return;
    }

    navigate("/UpdateProduct", { state: { product: selectedProduct } });
  };

  return (
    <div className="container mt-4">
      <div className="form-inline mb-3">
        <input
          type="text"
          className="form-control mr-2"
          placeholder="Search by ID"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
        <input
          type="text"
          className="form-control mr-2"
          placeholder="Search by Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <button className="btn btn-primary" onClick={searchHandler}>
          Search
        </button>
      </div>

      <table className="table table-striped table-hover">
        <thead className="thead-dark">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
          </tr>
        </thead>
        <tbody>
          {filteredProduct.map((product) => (
            <tr key={product.productId} onClick={() => selectHandler(product)}>
              <td>{product.productId}</td>
              <td>{product.name}</td>
              <td>{product.price}</td>
              <td>{product.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="mt-3">
        <button
          className="btn btn-danger mr-2"
          onClick={deleteHandler}
        >
          Delete Product
        </button>
        <button
          className="btn btn-info"
          onClick={updateHandler}
        >
          Update Product
        </button>
      </div>
    </div>
  );
}

export default ProductView;
