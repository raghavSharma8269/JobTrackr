import React from "react";

const UpdateJobIcon = () => {
  return (
    <i
      className="bi bi-pencil icon-btn"
      style={{
        fontSize: "2rem",
        cursor: "pointer",
        display: "inline-block",
        position: "absolute",
        top: "-3px",
        left: "600px",
      }}
      onClick={() => alert("Update Job")}
    />
  );
};
export default UpdateJobIcon;
