import React from "react";

const DeleteJobIcon = () => {
  return (
    <i
      className="bi bi-trash delete-icon-btn"
      style={{
        fontSize: "2rem",
        cursor: "pointer",
        display: "inline-block",
        position: "absolute",
        top: "-3px",
        left: "200px",
      }}
      onClick={() => alert("Delete Job")}
    />
  );
};
export default DeleteJobIcon;
