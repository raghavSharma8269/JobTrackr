import { useState } from "react";

const FavoriteStarComponent = () => {
    const [isStarred, setIsStarred] = useState(false);

    return (
        <button
            className="btn favorite-button"
            onClick={() => setIsStarred(!isStarred)}
            style={{
                fontSize: "2rem",
                color: isStarred ? "gold" : "#ccc",
                background: "none",
                border: "none",
                outline: "none",
                cursor: "pointer",
                marginBottom: "9px",
                transition:"color 0.3s ease",
            }}
            aria-label="Toggle Favorite"
        >
            {isStarred ? "⭐" : "☆"}
        </button>
    );
};

export default FavoriteStarComponent;
