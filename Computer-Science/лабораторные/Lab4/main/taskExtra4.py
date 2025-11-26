# taskExtra4.py
import task1          # contains load_schedule function
import taskExtra12    # manual TOML serialization
import taskExtra3     # manual XML serialization
import toml
import json
import time

# -----------------------------
def measure_handmade_toml(f, repeats=100):
    times = []
    for _ in range(repeats):
        start = time.time()
        data = task1.load_schedule(f)     # fixed function name
        taskExtra12.serialize(data)
        end = time.time()
        times.append(end - start)
    return times

def measure_library_toml(filename, repeats=100):
    times = []
    for _ in range(repeats):
        start = time.time()
        data = toml.load(filename)
        json.dumps(data)  # simulate serialization
        end = time.time()
        times.append(end - start)
    return times

def measure_handmade_xml(f, repeats=100):
    times = []
    for _ in range(repeats):
        start = time.time()
        data = task1.load_schedule(f)     # fixed function name
        taskExtra3.serialize(data)
        end = time.time()
        times.append(end - start)
    return times

# -----------------------------
# Read TOML file once for deserialization
with open('Schedule.toml', 'r', encoding='utf-8') as file:
    f = file.readlines()

# -----------------------------
# Measure times
handmade_toml_times = measure_handmade_toml(f)
library_toml_times = measure_library_toml('Schedule.toml')
handmade_xml_times = measure_handmade_xml(f)

# -----------------------------
# Function to print statistics
def print_stats(times, label):
    avg_time = sum(times) / len(times)
    max_time = max(times)
    min_time = min(times)
    print(f"\n{label}:")
    print(f"  Average time: {avg_time:.6f} s")
    print(f"  Maximum time: {max_time:.6f} s")
    print(f"  Minimum time: {min_time:.6f} s")

# -----------------------------
print_stats(handmade_toml_times, "Handmade TOML")
print_stats(library_toml_times, "Library TOML + JSON")
print_stats(handmade_xml_times, "Handmade XML")
