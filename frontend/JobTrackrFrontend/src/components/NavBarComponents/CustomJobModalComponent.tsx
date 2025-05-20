import React from "react";

const CustomJobModalComponent = () => {
  return (
    <div
      className="modal fade default-text-color"
      id="newCustomJobModal"
      tabIndex={-1}
      aria-labelledby="newCustomJobModal"
      aria-hidden="true"
    >
      <div className="modal-dialog">
        <div className="modal-content">
          {/* Modal Header */}
          <div className="modal-header light-bg">
            <h1 className="modal-title fs-5" id="newJobModalLabel">
              Add Custom Job
            </h1>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>

          {/* Modal Body */}
          <div className="modal-body light-bg">
            <h5>
              Job Title <span style={{ color: "#d9182b" }}>*</span>
            </h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job Title"
              required
            />

            <h5>
              Company <span style={{ color: "#d9182b" }}>*</span>
            </h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Company Name"
              required
            />

            <h5>
              Description <span style={{ color: "#d9182b" }}>*</span>
            </h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job Description"
              required
            />

            <h5>
              Job Page URL <span style={{ color: "#d9182b" }}>*</span>
            </h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job URL"
              required
            />

            <h5>Location</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job Location"
            />

            <h5>Salary</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Salary"
            />

            <div className="d-flex justify-content-center mb-4">
              <button className="btn purple-bg default-text-color">
                Add Job
              </button>
            </div>

            <div className="d-flex justify-content-end"></div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default CustomJobModalComponent;
