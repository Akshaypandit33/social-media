
import './App.css';
import Authentication from "./pages/Autentication/Authentication";
import HomePage from "./pages/HomePage/HomePage";
import {Route, Routes} from "react-router-dom";
import Register from "./pages/Autentication/Register";
import Profile from "./pages/Profile/Profile";

function App() {
  return (
    <div className="App">
        <Routes>

            <Route path="/*" element={<HomePage />} />
            <Route path="/*" element={<Authentication />} />

        </Routes>


    </div>
  );
}

export default App;
