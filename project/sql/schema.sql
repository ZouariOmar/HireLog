CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS hire_logs (
    log_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    event_type TEXT NOT NULL CHECK (event_type IN ('applied', 'interviewed', 'hired', 'rejected', 'other')),
    event_timestamp DATE NOT NULL,
    comments TEXT,

    -- Contraints
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS hire_log_attachments (
    attachment_id INTEGER PRIMARY KEY AUTOINCREMENT,
    log_id INTEGER NOT NULL,
    file_name TEXT NOT NULL,
    file_data BLOB NOT NULL,  -- stores the entire file content
    uploaded_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    -- Contraints
    FOREIGN KEY (log_id) REFERENCES hire_logs (log_id) ON DELETE CASCADE
);
