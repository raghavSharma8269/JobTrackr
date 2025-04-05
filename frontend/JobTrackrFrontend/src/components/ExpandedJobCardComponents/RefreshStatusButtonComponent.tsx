import React from 'react'

const RefreshStatusButtonComponent = () => {
    return (

        <i className="bi bi-arrow-clockwise icon-btn" style={{
            fontSize: "2rem",
            cursor: "pointer",
            display: "inline-block",
            position: "absolute",
            top: "-3px",
            left: "140px",

        }}

           onClick={() => alert("Reset")}
        ></i>
    )
            }
            export default RefreshStatusButtonComponent
