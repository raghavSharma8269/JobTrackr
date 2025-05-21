import React, { useEffect, useState } from "react";
import logo from "../assets/JobTrackr.png";
import logo2 from "../assets/vectorized.svg";
import LoginModalComponent from "../components/LoginModalComponent";
import RegisterModalComponent from "../components/RegisterModalComponent";
import { useNavigate } from "react-router-dom";

const WelcomePage: React.FC = () => {
  //changes tab name
  useEffect(() => {
    document.title = "JobVault"; // <- Your custom tab title
  }, []);

  const [isLoginModalVisible, setIsLoginModalVisible] = useState(false);
  const [isRegisterModalVisible, setIsRegisterModalVisible] = useState(false);

  const openLoginModal = () => setIsLoginModalVisible(true);
  const closeLoginModal = () => setIsLoginModalVisible(false);

  const openRegisterModal = () => setIsRegisterModalVisible(true);
  const closeRegisterModal = () => setIsRegisterModalVisible(false);

  const navigate = useNavigate();

  console.log("Rendering WelcomePage");

  return (
    <div className="container-fluid vh-100 d-flex align-items-center">
      <div className="row w-100">
        {/* Left Section - Title (Dark Background: #1c1d26) */}
        <div
          className="col-md-6 d-flex flex-column justify-content-start align-items-start text-white ps-5"
          style={{ backgroundColor: "#1c1d26", height: "100vh" }}
        >
          <h1
            className="display-4 mb-4 text-start fw-bold"
            style={{ marginTop: "35px" }}
          >
            Welcome To <br /> <span style={{ color: "#7400f0" }}>Job</span>
            Vault
          </h1>

          <img
            src={logo}
            alt="Job Trackr Logo"
            style={{ width: "500px", height: "500px", marginTop: "20px" }}
          />
        </div>

        {/* Right Section - Buttons (Light Dark Background: #292b38) */}
        <div
          className="col-md-6 d-flex flex-column justify-content-center align-items-center"
          style={{ backgroundColor: "#292b38", height: "100vh" }}
        >
          <div
            className="d-flex flex-column justify-content-center align-items-center mb-4"
            style={{ borderRadius: "0, 10px, 10px, 0", height: "100vh" }}
          >
            {/* Title Above Buttons */}
            <h1 className="display-4 text-white text-start mb-5 fw-bold">
              Begin Your <span style={{ color: "#7400f0" }}>Job</span> Hunt
            </h1>
            {/* Buttons */}

            {/* Register Button */}
            <button
              type="button"
              className="btn btn-lg mb-4 text-white"
              style={{ backgroundColor: "#7400f0", borderColor: "#7400f0" }}
              onClick={openRegisterModal}
            >
              Register
            </button>

            {/* Login Button */}
            <button
              type="button"
              className="btn btn-secondary btn-lg"
              onClick={openLoginModal} // This will open the modal when clicked
            >
              Login
            </button>

            {/*Contact Us Button */}
            <button
              type="button"
              className="btn btn-secondary btn-lg mt-xxl-5"
              onClick={() => navigate("/contact")}
            >
              Contact Us <i className="bi bi-question-circle" />
            </button>
          </div>
        </div>
      </div>

      {/* Login Modal Component */}
      <LoginModalComponent
        isVisible={isLoginModalVisible}
        closeModal={closeLoginModal}
      />
      <RegisterModalComponent
        isVisible={isRegisterModalVisible}
        closeModal={closeRegisterModal}
      />
    </div>
  );
};

export default WelcomePage;
