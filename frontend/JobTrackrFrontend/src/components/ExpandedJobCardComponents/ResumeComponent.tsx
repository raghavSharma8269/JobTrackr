import React, { FC, useEffect, useState } from "react";
import { Job } from "../../types/Job.ts";
import axios from "axios";

interface ResumeComponentProps {
  job: Job;
}

const ResumeComponent: FC<ResumeComponentProps> = ({ job }) => {
  const [feedback, setFeedback] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  // Update feedback when the job's resumeFeedback changes
  useEffect(() => {
    setFeedback(job.resumeFeedback || "");
  }, [job.resumeFeedback]);

  const handleGenerateResumeFeedback = async () => {
    if (!job || !job.id) {
      alert("Job ID is not available");
      return;
    }

    const id = job.id;

    setLoading(true);

    try {
      const response = await axios.get(
        `http://localhost:8080/api/profile/resume/${id}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        },
      );
      setFeedback(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error generating resume feedback:", error);
      alert("Failed to generate resume feedback");
      setLoading(false);
    }
  };

  return (
    <div>
      <button
        className="btn default-text-color"
        style={{ backgroundColor: "#7400f0", marginTop: "15px" }}
        onClick={handleGenerateResumeFeedback}
        disabled={loading} // prevent multiple clicks
      >
        {loading && (
          <div className="spinner-border spinner-border-sm me-2" role="status">
            <span className="visually-hidden" />
          </div>
        )}
        {loading ? "Generating..." : "Generate Resume Feedback"}
      </button>
      <p style={{ paddingTop: "15px" }}>
        <span style={{ color: "#d9182b" }}>**</span> Make sure you have your
        resume uploaded <span style={{ color: "#d9182b" }}>**</span>
      </p>

      {/* Feedback output */}
      {feedback && (
        <div
          className="mt-3 p-3 rounded"
          style={{
            backgroundColor: "#1c1d26",
            color: "#c9c9c9",
            whiteSpace: "normal", // allow line breaks
            border: "1px solid #7400f0",
          }}
          dangerouslySetInnerHTML={{ __html: feedback }} // render HTML
        ></div>
      )}
    </div>
  );
};

export default ResumeComponent;
