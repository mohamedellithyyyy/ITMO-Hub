import json
import yaml


with open("../data/Schedule.json", "r", encoding="utf-8") as f:
    data = json.load(f)


with open("../output/taskExtra2Result.yaml", "w", encoding="utf-8") as f:
    yaml.dump(data, f, allow_unicode=True, sort_keys=False)

print("Conversion done. Check Extra_Task2_Result.yaml")
