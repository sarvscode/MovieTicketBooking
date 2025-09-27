const API_BASE = "http://localhost:8080/api";

// Handle Login
const loginForm = document.getElementById("loginForm");
if (loginForm) {
  loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
      const res = await fetch(`${API_BASE}/user/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });
      const data = await res.json();
      if (res.ok) {
        localStorage.setItem("token", data.token);
        document.getElementById("loginMessage").textContent = "✅ Login successful!";
      } else {
        document.getElementById("loginMessage").textContent = "❌ " + data.message;
      }
    } catch (err) {
      document.getElementById("loginMessage").textContent = "⚠️ Error logging in!";
    }
  });
}

// Handle User Registration
const registerForm = document.getElementById("registerForm");
if (registerForm) {
  registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
      const res = await fetch(`${API_BASE}/user/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, email, password })
      });
      const data = await res.json();
      if (res.ok) {
        document.getElementById("registerMessage").textContent = "✅ Registration successful!";
      } else {
        document.getElementById("registerMessage").textContent = "❌ " + data.message;
      }
    } catch (err) {
      document.getElementById("registerMessage").textContent = "⚠️ Error registering!";
    }
  });
}

// Handle Booking
const bookingForm = document.getElementById("bookingForm");
if (bookingForm) {
  bookingForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");
    const bookingData = {
      showId: parseInt(document.getElementById("showId").value),
      userId: parseInt(document.getElementById("userId").value),
      numberOfSeats: parseInt(document.getElementById("numberOfSeats").value),
      seatNumbers: document.getElementById("seatNumbers").value.split(",")
    };

    try {
      const res = await fetch(`${API_BASE}/bookings`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token
        },
        body: JSON.stringify(bookingData)
      });
      const data = await res.json();
      if (res.ok) {
        document.getElementById("bookingMessage").textContent = "✅ Booking successful!";
      } else {
        document.getElementById("bookingMessage").textContent = "❌ " + data.message;
      }
    } catch (err) {
      document.getElementById("bookingMessage").textContent = "⚠️ Error booking!";
    }
  });
}

// Handle Admin Registration
const adminForm = document.getElementById("adminForm");
if (adminForm) {
  adminForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");
    const username = document.getElementById("adminUsername").value;
    const email = document.getElementById("adminEmail").value;
    const password = document.getElementById("adminPassword").value;

    try {
      const res = await fetch(`${API_BASE}/admin/registeradmin`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " +  localStorage.getItem("token")
        },
        body: JSON.stringify({ username, email, password })
      });
      const data = await res.json();
      if (res.ok) {
        document.getElementById("adminMessage").textContent = "✅ Admin registered!";
      } else {
        document.getElementById("adminMessage").textContent = "❌ " + data.message;
      }
    } catch (err) {
      document.getElementById("adminMessage").textContent = "⚠️ Error registering admin!";
    }
  });
}

// Load all shows on homepage
if (document.getElementById("showList")) {
  fetch(`${API_BASE}/show/all`)
    .then(res => res.json())
    .then(shows => {
      const list = document.getElementById("showList");
      list.innerHTML = "";
      shows.forEach(show => {
        const li = document.createElement("li");
        li.textContent = `${show.movie.movieName} at ${show.theater.theaterName} - ${show.showTime}`;
        list.appendChild(li);
      });
    })
    .catch(err => console.error("Error loading shows:", err));
}


// Populate show dropdown in booking form
const showDropdown = document.getElementById("showId");
if (showDropdown) {
  fetch(`${API_BASE}/show/all`)
    .then(res => res.json())
    .then(shows => {
      shows.forEach(show => {
        const option = document.createElement("option");
        option.value = show.id;
        option.textContent = `${show.movie.movieName} at ${show.theater.theaterName} - ${show.showTime}`;
        showDropdown.appendChild(option);
      });
    })
    .catch(err => console.error("Error loading shows:", err));
}

