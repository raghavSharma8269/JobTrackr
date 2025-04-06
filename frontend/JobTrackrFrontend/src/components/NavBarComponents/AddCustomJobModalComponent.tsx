import React from "react";

const AddCustomJobModalComponent = () => {
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
            <h5>Job Title</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job Title"
              required
            />

            <h5>Company</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Company Name"
              required
            />

            <h5>Description</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job Description"
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

            <h5>Job Level</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job Level"
            />

            <h5>Job Page URL</h5>
            <input
              type="text"
              className="form-control light-bg mb-4"
              id="jobLink"
              placeholder="Enter Job URL"
              required
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
export default AddCustomJobModalComponent;
