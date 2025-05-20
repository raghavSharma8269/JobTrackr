import React from "react";
import CustomJobModalComponent from "./CustomJobModalComponent.tsx";
import axios from "axios";

const AddJobModalComponent = () => {
  const [jobUrl, setJobUrl] = React.useState("");

  const handleAddingJobViaLinkedIn = async () => {
    try {
      const token = localStorage.getItem("token");
      const request = {
        jobUrl,
      };

      console.log("REQUEST DUDE: " + request.jobUrl);

      await axios.post(
        `http://localhost:8080/api/jobs/create?url=${encodeURIComponent(jobUrl)}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );

      // Reset form
      setJobUrl("");

      alert("Job added successfully!");
    } catch (error) {
      console.error("Error adding job:", error);

      if (axios.isAxiosError(error) && error.response) {
        const backendMessage = error.response.data;

        if (backendMessage.message) {
          alert(backendMessage.message);
        }
      } else {
        alert("An unknown error occurred.");
      }
    }
  };

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
                placeholder="Enter Job Link"
                required
                value={jobUrl}
                onChange={(e) => setJobUrl(e.target.value)}
              />
              <div className="d-flex justify-content-center mb-4">
                <button
                  className="btn purple-bg default-text-color"
                  onClick={handleAddingJobViaLinkedIn}
                >
                  Add Job
                </button>
              </div>

              <h5 className="text-center mb-3">OR</h5>

              <div className="d-flex justify-content-center mb-4">
                <button
                  className="btn purple-bg default-text-color"
                  data-bs-toggle="modal"
                  data-bs-target="#newCustomJobModal"
                >
                  Add Custom Job
                </button>
              </div>
              <div className="d-flex justify-content-end"></div>
            </div>
          </div>
        </div>
      </div>
      <CustomJobModalComponent />
    </>
  );
};

export default AddJobModalComponent;
