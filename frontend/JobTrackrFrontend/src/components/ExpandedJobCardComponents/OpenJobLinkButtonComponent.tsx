import React from 'react'

const OpenJobLinkButtonComponent = () => {
    return (
        <i
            className="bi bi-arrow-up-right-square default-text-color open-job-link-btn"
            style={{
                fontSize: "2rem",
                cursor: "pointer",
                display: "inline-block",
            }}
            onClick={() => alert("Clicked")}
        ></i>
    );
};

export default OpenJobLinkButtonComponent;
