CREATE TABLE t_tasks (
    id serial primary key,
    title varchar(255),
    description varchar(255),
    deadline_date date,
    status int,
    user_id bigint
)