message="Welcome to my shell"
echo $message 
echo "Current PATH: $PATH"
echo "Home directory: " $HOME
echo "Running script as user: " $USER 
echo "Default shell: "$SHELL
MYVAR="Hello"
echo "1. Dollar Sign: $MYVAR"
echo "2. Backtick: Today is `date`"
echo "3. Double Quotes: $MYVAR World"
echo '4. Single Quotes: $MYVAR World'
echo "5. Backslash: \$MYVAR is not expanded literally"
echo -e "lab0/excadrill4" | grep ".txt"
