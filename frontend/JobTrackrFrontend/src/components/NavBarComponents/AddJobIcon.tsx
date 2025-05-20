import React from "react";
import AddJobModalComponent from "./AddJobModalComponent.tsx";

const AddJobIcon = () => {
  return (
    <>
      <i
        className="bi bi-plus-circle icon-btn default-text-color"
        style={{
          fontSize: "2rem",
          cursor: "pointer",
          display: "inline-block",
          position: "absolute",
          top: "10px",
          left: "400px",
        }}
        data-bs-toggle="modal"
        data-bs-target="#newJobModal" // This must match the modal ID exactly
      />
      <AddJobModalComponent />
    </>
  );
};

export default AddJobIcon;
