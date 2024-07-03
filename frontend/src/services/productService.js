const apiUrl = "http://localhost:8080/api/product";

export async function getAllProducts() {
  const response = await fetch(apiUrl);

  const data = await response.json();

  return data;
}

export async function getProductById(id) {
  const url = apiUrl + "/" + id;

  const response = await fetch(url);

  if (!response.ok) {
    throw new Error("There is no product with given ID!");
  }
  const data = await response.json();

  return data;
}

export async function getProductByExample(product) {
  const response = await fetch(apiUrl + "/search", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  });

  const data = await response.json();

  return data;
}

export async function createProduct(product) {
  const response = await fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  });

  const data = await response.json();

  return data;
}

export async function saveProduct(product, id) {
  const response = await fetch(apiUrl + "/" + id, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  });

  if (!response.ok) {
    throw new Error("There is no product with given ID!");
  }
  const data = await response.json();

  return data;
}

export async function deleteProduct(id) {
  const response = await fetch(apiUrl + "/" + id, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("There is no product with given ID!");
  }
  const data = await response.text();

  return data;
}
