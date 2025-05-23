import React, { FC, useContext, useEffect, useState } from "react";
import axios from "axios";
import { Job } from "../../types/Job.ts";
import { UserContext } from "../../context/UserContext";

interface CvComponentProps {
  job: Job;
}

const CvComponent: FC<CvComponentProps> = ({ job }) => {
  const [feedback, setFeedback] = useState<string>(job.cvFeedback || "");
  const [loading, setLoading] = useState<boolean>(false);
  const { user, loading: userLoading, refreshUser } = useContext(UserContext);

  useEffect(() => {
    setFeedback(job.cvFeedback || "");
  }, [job.cvFeedback]);

  const handleGenerateCoverLetter = async () => {
    if (!job?.id) {
      alert("Job ID is not available");
      return;
    }

    if (user && user.numOfAiRequests >= 8) {
      alert(
        "❌ You've reached your max of 8 AI cover letter generation requests.",
      );
      return;
    }

    setLoading(true);

    try {
      const response = await axios.get(
        `http://localhost:8080/api/profile/cv/${job.id}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        },
      );

      setFeedback(response.data); // ✅ display immediately
      await refreshUser(); // ✅ update request count
    } catch (error) {
      console.error("Error generating cover letter:", error);
      alert("Failed to generate cover letter");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <button
        className="btn default-text-color"
        style={{ backgroundColor: "#7400f0", marginTop: "15px" }}
        onClick={handleGenerateCoverLetter}
        disabled={loading}
      >
        {loading && (
          <div className="spinner-border spinner-border-sm me-2" role="status">
            <span className="visually-hidden" />
          </div>
        )}
        {loading ? "Generating..." : "Generate Cover Letter"}
      </button>

      <p style={{ marginTop: "15px" }}>
        <span style={{ color: "#d9182b" }}>**</span> Make sure you have your
        resume and cover letter uploaded{" "}
        <span style={{ color: "#d9182b" }}>**</span>
      </p>

      {!userLoading && user && (
        <p style={{ marginTop: "10px", color: "#9e9ca1" }}>
          🔁 You have used {user.numOfAiRequests} of 8 AI cover letter requests.
        </p>
      )}

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

export default CvComponent;
