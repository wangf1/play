PROGRAM hello (output);

{Write 'Hello, compiler''s world.' ten times.}

VAR
    i : integer;

BEGIN {hello}
    FOR i := 1 TO 10 DO BEGIN
        writeln('Hello, compiler''s world.');
    END;
END {hello}.
