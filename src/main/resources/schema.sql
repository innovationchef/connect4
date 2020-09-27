DROP TABLE IF EXISTS CONNECT4_GAME_DATA;
DROP TABLE IF EXISTS CONNECT4_MATCH_DATA;
DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;
CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE TABLE CONNECT4_GAME_DATA (
    TID INT,
    SESSION_ID VARCHAR,
    DATA BLOB,
    NEXT_PLAYER INT,
    ROWN INT,
    COLN INT,
    NUM INT,
    CURR_MOVE INT,
    IS_SESSION_OVER VARCHAR,
    PRIMARY KEY (TID)
);

CREATE TABLE CONNECT4_MATCH_DATA (
    TID INT,
    SESSION_ID VARCHAR,
    ROWN INT,
    COLN INT,
    PLAYER INT,
    MOVE INT,
    PRIMARY KEY (TID)
);