import React from "react";

const AddJobModalComponent = () => {
  return (
    <div
      className="modal fade default-text-color"
      id="newJobModal" // No '#'
      tabIndex={-1}
      aria-labelledby="newJobModalLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header light-bg">
            <h1 className="modal-title fs-5" id="newJobModalLabel">
              Add Job
            </h1>
          </div>
          <div className="modal-body light-bg">
            <h3>Enter Job Via LinkedIn Link</h3>

            <label htmlFor="jobLink" className="form-label">
              Job Link
            </label>
            <input
              type="text"
              className="form-control light-bg"
              id="jobLink"
              placeholder="Enter Job Link"
            />
            <div className="modal-footer light-bg">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddJobModalComponent;
