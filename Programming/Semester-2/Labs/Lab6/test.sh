#!/bin/bash

# ─────────────────────────────────────────
# Lab6 - MusicBand Manager - Test Script
# ─────────────────────────────────────────

GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

PASS=0
FAIL=0

SERVER_PID=""

# ─────────────────────────────────────────
# SETUP
# ─────────────────────────────────────────

compile() {
    echo -e "${BLUE}Compiling...${NC}"
    mkdir -p out/common out/server out/client

    javac -d out/common \
        common/src/main/java/models/*.java 2>&1

    javac -cp out/common -d out/common \
        common/src/main/java/network/*.java 2>&1

    javac -cp out/common -d out/server \
        server/src/main/java/exceptions/*.java \
        server/src/main/java/managers/*.java \
        server/src/main/java/utility/*.java \
        server/src/main/java/commands/*.java \
        server/src/main/java/network/*.java \
        server/src/main/java/ServerMain.java 2>&1

    javac -cp out/common -d out/client \
        client/src/main/java/console/*.java \
        client/src/main/java/network/*.java \
        client/src/main/java/ClientMain.java 2>&1

    echo -e "${GREEN}✅ Compiled${NC}"
}

start_server() {
    # kill any old server
    pkill -f ServerMain 2>/dev/null

    export MUSIC_COLLECTION_FILE=data/bands.xml
    java -cp out/common:out/server ServerMain &
    SERVER_PID=$!

    sleep 2
    echo -e "${GREEN}✅ Server started (PID: $SERVER_PID)${NC}"
}

stop_server() {
    if [ -n "$SERVER_PID" ]; then
        kill $SERVER_PID 2>/dev/null
        wait $SERVER_PID 2>/dev/null
        sleep 2
        echo -e "${YELLOW}Server stopped${NC}"
    fi
}

# ─────────────────────────────────────────
# TEST RUNNER
# ─────────────────────────────────────────

print_test() {
    echo -e "\n${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    echo -e "${YELLOW}TEST: $1${NC}"
    echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
}

run_command() {
    local description="$1"
    local input="$2"
    local expected="$3"

    echo -e "\n${YELLOW}▶ $description${NC}"

    output=$(echo -e "${input}\nexit" | java -cp out/common:out/client ClientMain 2>&1)

    if echo "$output" | grep -qE "$expected"; then
        echo -e "${GREEN}✅ PASS — found: '$expected'${NC}"
        ((PASS++))
    else
        echo -e "${RED}❌ FAIL — expected: '$expected'${NC}"
        echo -e "Output was:\n$output"
        ((FAIL++))
    fi

    sleep 1
}

# ─────────────────────────────────────────
# FIXED INPUT (important!)
# ─────────────────────────────────────────

BAND_INPUT="Test Band\n1.0\n100\n5\n4\nJAZZ\nJohn\n1990-01-01\nPASS123\nRED\ny\n1.0\n1\n1"

# ─────────────────────────────────────────
# RUN
# ─────────────────────────────────────────

compile
start_server

# ─────────────────────────────────────────
print_test "1. HELP COMMAND"
# ─────────────────────────────────────────
run_command "help" "help" "add - Add a new band"

# ─────────────────────────────────────────
print_test "2. INFO COMMAND"
# ─────────────────────────────────────────
run_command "info" "info" "Type:"
run_command "info date" "info" "Init date:"
run_command "info size" "info" "Size:"

# ─────────────────────────────────────────
print_test "3. ADD COMMAND"
# ─────────────────────────────────────────
run_command "add band" \
    "add\n${BAND_INPUT}" \
    "Added|success"

# ─────────────────────────────────────────
print_test "4. SHOW COMMAND"
# ─────────────────────────────────────────
run_command "show table" \
    "show" \
    "Total:|ID"

# ─────────────────────────────────────────
print_test "5. HEAD COMMAND"
# ─────────────────────────────────────────
run_command "head" \
    "head" \
    "MusicBand|empty"

# ─────────────────────────────────────────
print_test "6. REMOVE_BY_ID"
# ─────────────────────────────────────────
run_command "remove invalid" \
    "remove_by_id 9999" \
    "Not found"

run_command "remove valid" \
    "remove_by_id 1" \
    "Removed|Not found"

# ─────────────────────────────────────────
print_test "7. UPDATE COMMAND"
# ─────────────────────────────────────────
run_command "add before update" \
    "add\n${BAND_INPUT}" \
    "Added"

run_command "update invalid" \
    "update 9999\n${BAND_INPUT}" \
    "Not found"

run_command "update valid" \
    "update 1\n${BAND_INPUT}" \
    "Updated|success"

# ─────────────────────────────────────────
print_test "8. REMOVE_HEAD"
# ─────────────────────────────────────────
run_command "remove_head" \
    "remove_head" \
    "Removed|empty"

# ─────────────────────────────────────────
print_test "9. CLEAR"
# ─────────────────────────────────────────
run_command "add before clear" \
    "add\n${BAND_INPUT}" \
    "Added"

run_command "clear" \
    "clear" \
    "cleared"

run_command "show empty" \
    "show" \
    "Total: 0|OK"

# ─────────────────────────────────────────
print_test "10. SUM"
# ─────────────────────────────────────────
run_command "add before sum" \
    "add\n${BAND_INPUT}" \
    "Added"

run_command "sum" \
    "sum_of_number_of_participants" \
    "[0-9]"

# ─────────────────────────────────────────
print_test "11. FILTER"
# ─────────────────────────────────────────
run_command "filter match" \
    "filter_starts_with_name Test" \
    "Test Band|No bands"

run_command "filter no match" \
    "filter_starts_with_name XYZXYZ" \
    "No bands"

# ─────────────────────────────────────────
print_test "12. UNIQUE"
# ─────────────────────────────────────────
run_command "unique participants" \
    "print_unique_number_of_participants" \
    "[0-9]|\[\]"

# ─────────────────────────────────────────
print_test "13. REMOVE_LOWER"
# ─────────────────────────────────────────
run_command "remove lower" \
    "remove_lower\n${BAND_INPUT}" \
    "removed|Lower"

# ─────────────────────────────────────────
print_test "14. EXECUTE_SCRIPT"
# ─────────────────────────────────────────

cat > /tmp/lab6_script.txt << 'EOF'
info
show
sum_of_number_of_participants
EOF

run_command "execute script" \
    "execute_script /tmp/lab6_script.txt" \
    "Type:|Size:"

run_command "script not found" \
    "execute_script /tmp/none.txt" \
    "error|not found"

# ─────────────────────────────────────────
print_test "15. SERVER DOWN"
# ─────────────────────────────────────────
stop_server

run_command "server unavailable" \
    "help" \
    "error|unavailable|refused"

start_server

# ─────────────────────────────────────────
print_test "16. UNKNOWN"
# ─────────────────────────────────────────
run_command "unknown" \
    "unknown_command_xyz" \
    "Unknown command"

# ─────────────────────────────────────────
print_test "17. EXIT"
# ─────────────────────────────────────────
run_command "exit" \
    "exit" \
    "Exiting|bye"

# ─────────────────────────────────────────
# CLEANUP
# ─────────────────────────────────────────
stop_server

# ─────────────────────────────────────────
# SUMMARY
# ─────────────────────────────────────────
echo -e "\n${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo -e "${YELLOW}TEST SUMMARY${NC}"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
echo -e "${GREEN}✅ PASSED: $PASS${NC}"
echo -e "${RED}❌ FAILED: $FAIL${NC}"
TOTAL=$((PASS + FAIL))
echo -e "TOTAL: $TOTAL"
echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"