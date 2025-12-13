import task1, taskExtra1, taskExtra3 
import yaml, json, time
import os  # for devnull


with open("../data/schedule.json", "r", encoding="utf-8") as f:
    json_text = f.read()

with open("../output/taskExtra2Result.yaml", "r", encoding="utf-8") as f:
    yaml_text = f.read()


# 1. Manual parser + YAML
start = time.time()
for _ in range(100):
    data_manual_yaml = task1.parse(json_text)
    with open(os.devnull, "w", encoding="utf-8") as f:
        taskExtra1.to_yaml(data_manual_yaml, f)
end = time.time()
print("Manual parser + YAML:", end - start, "sec")


# 2. YAML library (PyYAML)
start = time.time()
for _ in range(100):
    data_lib_yaml = yaml.safe_load(yaml_text)
    json.dumps(data_lib_yaml)
end = time.time()
print("YAML library:", end - start, "sec")


# 3. Manual parser + XML
start = time.time()
for _ in range(100):
    data_manual_xml = task1.parse(json_text)
    xml_string = taskExtra3.dict_to_xml(data_manual_xml, "schedule_data")
end = time.time()
print("Manual parser + XML:", end - start, "sec")
