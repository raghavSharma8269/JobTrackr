import { useState } from "react";
import ExpandedJobCard from "./ExpandedJobCard";

const JobCardComponent = () => {
    const [isExpanded, setIsExpanded] = useState(false);
    const openExpanded = () => setIsExpanded(true);

    return (
        <div className="container">
            <div className="row-100">
                <button
                    type="button"
                    className="btn btn-lg w-100" // Ensures full width
                    style={{
                        backgroundColor: "#292b38",
                        color: "#c9c9c9",
                        height: "140px",
                        marginBottom: "5px",
                    }}
                    onClick={openExpanded}
                >
                    <div className="container align-items-start text-start">
                        <h5>Job Title</h5>
                        <p style={{ fontSize: "15px" }}>Company Name</p>
                        <p style={{ fontSize: "15px" }}>Location</p>
                    </div>
                </button>
            </div>
            <ExpandedJobCard isVisible={isExpanded} />
        </div>
    );
};


export default JobCardComponent;
