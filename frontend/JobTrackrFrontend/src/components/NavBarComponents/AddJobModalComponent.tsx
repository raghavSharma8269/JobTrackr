import React from "react";
import AddCustomJobModalComponent from "./AddCustomJobModalComponent.tsx";
import * as bootstrap from "bootstrap";

const AddJobModalComponent = () => {
  return (
    <>
      <div
        className="modal fade default-text-color"
        id="newJobModal"
        tabIndex={-1}
        aria-labelledby="newJobModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            {/* Modal Header */}
            <div className="modal-header light-bg default-text-color">
              <h1 className="modal-title fs-5" id="newJobModalLabel">
                Add Job
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>

            {/* Modal Body */}
            <div className="modal-body light-bg default-text-color">
              <h3 className="mb-3">
                Enter Job Via <span style={{ color: "#0077B5" }}>LinkedIn</span>{" "}
                Link
              </h3>

              <input
                type="text"
                className="form-control light-bg mb-4"
                id="jobLink"
                placeholder="Enter Job Link"
              />
              <div className="d-flex justify-content-center mb-4">
                <button className="btn purple-bg default-text-color">
                  Add Job
                </button>
              </div>

              <h5 className="text-center mb-3">OR</h5>

              <div className="d-flex justify-content-center mb-4">
                <button className="btn purple-bg default-text-color">
                  Enter Custom Job Details
                </button>
              </div>
              <div className="d-flex justify-content-end"></div>
            </div>
          </div>
        </div>
      </div>
      <AddCustomJobModalComponent />
    </>
  );
};

export default AddJobModalComponent;
