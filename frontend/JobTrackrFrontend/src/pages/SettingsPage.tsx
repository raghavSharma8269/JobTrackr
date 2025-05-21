import React, { useEffect, useState } from "react";

interface SettingsPageProps {
  closeSettingsPage: () => void;
}

const SettingsPage: React.FC<SettingsPageProps> = ({}) => {
  //changes tab name
  useEffect(() => {
    document.title = "Settings | JobVault"; // <- Your custom tab title
  }, []);

  const [resume, setResume] = useState<File | null>(null);
  const [coverLetter, setCoverLetter] = useState<File | null>(null);

  const handleResumeUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file && file.type === "application/pdf") {
      setResume(file);
    } else {
      alert("Please upload a PDF file.");
      event.target.value = ""; // Reset input if the file is not a PDF
    }
  };

  const handleCoverLetterUpload = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const file = event.target.files?.[0];
    if (file && file.type === "application/pdf") {
      setCoverLetter(file);
    } else {
      alert("Please upload a PDF file.");
      event.target.value = ""; // Reset input if the file is not a PDF
    }
  };

  return (
    <div className="container-fluid text-center">
      {/* Resume Section */}
      <div
        className="p-4"
        style={{
          backgroundColor: "#292b38",
          marginLeft: "40px",
          marginRight: "40px",
          borderRadius: "10px",
        }}
      >
        <h1 className="default-text-color">Resume</h1>

        {resume ? (
          <p style={{ color: "#c9c9c9" }}>Uploaded: {resume.name}</p>
        ) : (
          <p style={{ color: "#c9c9c9" }}>No resume uploaded</p>
        )}

        {/* Custom "Choose File" Button */}
        <label
          htmlFor="resume-upload"
          style={{
            display: "inline-block",
            padding: "10px 20px",
            backgroundColor: "#1c1d26",
            color: "#ffffff",
            cursor: "pointer",
            borderRadius: "5px",
            marginTop: "10px",
          }}
        >
          Choose PDF File
        </label>
        <input
          id="resume-upload"
          type="file"
          accept="application/pdf"
          className="form-control mt-2"
          onChange={handleResumeUpload}
          style={{
            display: "none", // Hide the default file input
          }}
        />
      </div>

      {/* Spacer */}
      <div className="py-3" style={{ backgroundColor: "#1c1d26" }}></div>

      {/* Cover Letter Section */}
      <div
        className="p-4"
        style={{
          backgroundColor: "#292b38",
          marginLeft: "40px",
          marginRight: "40px",
          borderRadius: "10px",
        }}
      >
        <h1 className="default-text-color">Cover Letter</h1>

        {coverLetter ? (
          <p style={{ color: "#c9c9c9" }}>Uploaded: {coverLetter.name}</p>
        ) : (
          <p style={{ color: "#c9c9c9" }}>No cover letter uploaded</p>
        )}

        {/* Custom "Choose File" Button */}
        <label
          htmlFor="cover-letter-upload"
          style={{
            display: "inline-block",
            padding: "10px 20px",
            backgroundColor: "#1c1d26",
            color: "#ffffff",
            cursor: "pointer",
            borderRadius: "5px",
            marginTop: "10px",
          }}
        >
          Choose PDF File
        </label>
        <input
          id="cover-letter-upload"
          type="file"
          accept="application/pdf"
          className="form-control mt-2"
          onChange={handleCoverLetterUpload}
          style={{
            display: "none", // Hide the default file input
          }}
        />
      </div>

      {/* Spacer */}
      <div className="py-3" style={{ backgroundColor: "#1c1d26" }}></div>

      {/* Change Password Section */}
      <div
        className="p-4"
        style={{
          backgroundColor: "#292b38",
          marginLeft: "40px",
          marginRight: "40px",
          borderRadius: "10px",
        }}
      >
        <h1 className="default-text-color">Change Password</h1>
        <form>
          <div className="mb-3 mt-4">
            <input
              type="password"
              className="form-control default-placeholder-color"
              placeholder="Current Password"
              style={{ backgroundColor: "#1c1d26", borderColor: "#1c1d26" }}
              required
            />
          </div>
          <div className="mb-3">
            <input
              type="password"
              className="form-control default-placeholder-color"
              placeholder="New Password"
              style={{ backgroundColor: "#1c1d26", borderColor: "#1c1d26" }}
              required
            />
          </div>
          <div className="mb-3 text-white">
            <input
              type="password"
              className="form-control default-placeholder-color"
              placeholder="Confirm New Password"
              style={{ backgroundColor: "#1c1d26", borderColor: "#1c1d26" }}
              required
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary mt-2"
            style={{
              backgroundColor: "#1c1d26",
              borderColor: "#1c1d26",
            }}
          >
            Change Password
          </button>
        </form>
      </div>
    </div>
  );
};

export default SettingsPage;
