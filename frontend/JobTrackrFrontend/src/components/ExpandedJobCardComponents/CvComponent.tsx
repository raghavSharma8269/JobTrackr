import React from "react";

const CvComponent = () => {
  return (
    <div>
      <button
        className="btn default-text-color"
        style={{ backgroundColor: "#7400f0", marginTop: "15px" }}
      >
        Generate Cover Letter
      </button>
      <p style={{ marginTop: "20px" }}>
        <span style={{ color: "#d9182b" }}>**</span> Make sure you have your
        resume and cover letter uploaded{" "}
        <span style={{ color: "#d9182b" }}>**</span>
      </p>
    </div>
  );
};
export default CvComponent;
