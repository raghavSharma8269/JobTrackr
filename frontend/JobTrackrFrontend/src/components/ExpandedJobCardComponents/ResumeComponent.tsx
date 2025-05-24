import React, { FC, useContext, useEffect, useState } from "react";
import { Job } from "../../types/Job.ts";
import axios from "axios";
import { UserContext } from "../../context/UserContext";

interface ResumeComponentProps {
  job: Job;
}

const ResumeComponent: FC<ResumeComponentProps> = ({ job }) => {
  const [feedback, setFeedback] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const { user, loading: userLoading, refreshUser } = useContext(UserContext);

  useEffect(() => {
    setFeedback(job.resumeFeedback || "");
  }, [job.resumeFeedback]);

  const handleGenerateResumeFeedback = async () => {
    if (!job?.id) {
      alert("Job ID is not available");
      return;
    }

    if (user && user.numOfAiRequests >= 8) {
      alert("❌ You've reached your max of 8 AI resume feedback requests.");
      return;
    }

    setLoading(true);

    try {
      const response = await axios.get(
        `http://localhost:8080/api/profile/resume/${job.id}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        },
      );
      setFeedback(response.data);
      await refreshUser(); // update AI usage count after successful call
    } catch (error) {
      console.error("Error generating resume feedback:", error);
      alert("Failed to generate resume feedback");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <button
        className="btn default-text-color"
        style={{ backgroundColor: "#7400f0", marginTop: "15px" }}
        onClick={handleGenerateResumeFeedback}
        disabled={loading}
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

      {/* Show AI usage below button */}
      {!userLoading && user && (
        <p style={{ marginTop: "10px", color: "#9e9ca1" }}>
          🔁 You have {8 - user.numOfAiRequests} AI requests remaining
        </p>
      )}

      {/* Render resume feedback if available */}
      {feedback && (
        <div
          className="mt-3 p-3 rounded"
          style={{
            backgroundColor: "#1c1d26",
            color: "#c9c9c9",
            whiteSpace: "normal",
            border: "1px solid #7400f0",
          }}
          dangerouslySetInnerHTML={{ __html: feedback }}
        />
      )}
    </div>
  );
};

export default ResumeComponent;
