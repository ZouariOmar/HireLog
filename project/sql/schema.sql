CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS hire_logs (
    log_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    job_title TEXT NOT NULL,
    event_type TEXT NOT NULL CHECK (event_type IN ('applied', 'interviewed', 'hired', 'rejected', 'other')),
    event_date DATE NOT NULL,
    comments TEXT,

    -- Contraints
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
