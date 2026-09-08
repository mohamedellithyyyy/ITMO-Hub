TARGET     = printer

SRCDIR=.
OBJDIR=obj
DEPDIR=dep

CFLAGS     = -g -O2 -Wall -Werror -std=c17 -Wno-unused-function -Wdiscarded-qualifiers -Wincompatible-pointer-types -Wint-conversion -fno-plt
CC         = gcc
LD         = gcc

SRCS:=$(wildcard $(SRCDIR)/*.c)
OBJS:=$(patsubst $(SRCDIR)/%.c,$(OBJDIR)/%.o,$(SRCS))
DEPS:=$(patsubst $(SRCDIR)/%.c,$(DEPDIR)/%.d,$(SRCS))

.PHONY: all
all: $(TARGET)

$(TARGET): $(OBJS) $(DEPS)
	$(LD) -o $@ $(OBJS)

$(OBJDIR):
	mkdir -p $(OBJDIR)

$(OBJDIR)/%.o: $(SRCDIR)/%.c $(OBJDIR)
	$(CC) -c $(CFLAGS) -o $@ $<

$(DEPDIR):
	mkdir -p $(DEPDIR)

$(DEPDIR)/%.d: %.c $(DEPDIR)
	mkdir -p $(DEPDIR)
	$(CC) $(CFLAGS) -M -MF $@ -MQ $(patsubst $(DEPDIR)/%.d,$(OBJDIR)/%.o,$@) $<
	$(CC) $(CFLAGS) -M -MQ $@ $< >> $@

.PHONY: clean
clean: 
	$(RM) -r $(OBJDIR) $(DEPDIR) $(TARGET)

.PHONY: run
run: $(TARGET)
	./$(TARGET)

.PHONY: rebuild
rebuild: clean all

include $(DEPS)

