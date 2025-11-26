# ExtraTask2.py
import pickle, json,toml,yaml

def nest_lessons(day_dict):
    nested = {}
    for key, value in day_dict.items():
        if "_" in key:
            lesson, field = key.split("_", 1)
            if lesson not in nested:
                nested[lesson] = {}
            nested[lesson][field] = value
        else:
            nested[key] = value
    return nested

# Load binary object
with open("Schedule.bin", "rb") as f:
    data = pickle.load(f)

# -----------------------------
# 1. JSON
with open("ExtraTask2.json", "w", encoding="utf-8") as f:
    json.dump(data, f, ensure_ascii=False, indent=2)

# -----------------------------
# 2. TOML
toml_data = {day: nest_lessons(lessons) for day, lessons in data.items()}
with open("ExtraTask2.toml", "w", encoding="utf-8") as f:
    toml.dump(toml_data, f)

# -----------------------------
# 3. YAML
with open("ExtraTask2.yaml", "w", encoding="utf-8") as f:
    yaml.dump(data, f, allow_unicode=True, sort_keys=False)

print("Files generated using libraries:")
print(" - ExtraTask2.json")
print(" - ExtraTask2.toml")
print(" - ExtraTask2.yaml")
