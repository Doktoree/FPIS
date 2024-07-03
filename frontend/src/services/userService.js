const apiUrl = "http://localhost:8080/api/user";

export async function checkUser(user) {
  console.log(user + ' url: ' + apiUrl);

  const response = await fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  if (!response.ok) {
    throw new Error("nIJE uSPELO");
  }

  const data = await response.json();

  console.log("Uspelo");
  return data;
}
