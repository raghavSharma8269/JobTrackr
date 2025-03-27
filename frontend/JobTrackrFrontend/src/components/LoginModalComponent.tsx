import React, { useState } from "react";
import "./ModalComponents.css";

interface LoginModalComponentProps {
  isVisible: boolean;
  closeModal: () => void;
}

const LoginModalComponent: React.FC<LoginModalComponentProps> = ({
  isVisible,
  closeModal,
}) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  if (!isVisible) return null; // Don't render the modal if it's not visible

  return (
    <div className="modal show" tabIndex={-1} style={{ display: "block" }}>
      <div className="modal-dialog">
        <div className="modal-content" style={{ backgroundColor: "#292b38" }}>
          <div className="modal-header">
            <h5 className="modal-title text-white">Login</h5>
            <button
              type="button"
              className="btn-close"
              onClick={closeModal}
              aria-label="Close"
            ></button>
          </div>
          <div className="modal-body text-white">
            <form>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">
                  Email Address
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  placeholder="Enter your email"
                  style={{
                    backgroundColor: "#1c1d26",
                    color: "white",
                    borderColor: "#1c1d26",
                  }}
                  required
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">
                  Password
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Enter your password"
                  style={{
                    backgroundColor: "#1c1d26",
                    color: "white",
                    borderColor: "#1c1d26",
                  }}
                  required
                />
              </div>
            </form>
          </div>
          <div className="modal-footer">
            <button
              type="button"
              className="btn btn-secondary"
              onClick={closeModal}
            >
              Close
            </button>
            <button
              type="button"
              className="btn text-white"
              style={{ backgroundColor: "#7400f0", borderColor: "#7400f0" }}
            >
              Login
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginModalComponent;
