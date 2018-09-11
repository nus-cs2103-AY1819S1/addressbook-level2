#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./actual.txt" ]
then
    rm actual.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin ../src/seedu/addressbook/Main.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

#creates a test_import.txt file
if [ ! -e "./test_import.txt" ]
then
    echo "Edric Teo p/91111111 e/etjk@mail.com a/1, Ang Mo Kio Ave 1, #01-11 t/friends" > test_import.txt
    echo "Koh Zheng Wei p/92222222 e/kzw@mail.com a/2, Jln Bukit Batok, #02-22 t/friends" >> test_import.txt
    echo "Reuben Tan p/93333333 e/rtjl@mail.com a/3, Yishun Ave 3, #03-33 t/friends" >> test_import.txt
fi


# run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ../bin seedu.addressbook.Main < input.txt > actual.txt

# compare the output to the expected output
diff actual.txt expected.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
