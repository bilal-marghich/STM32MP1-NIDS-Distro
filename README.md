# STM32MP1 NIDS Distro

## Overview

This project aims to develop a lightweight embedded Linux platform for CAN bus intrusion detection on the STM32MP135F-DK. The project compares two intrusion detection approaches:

- **Rule-based detection:** Detects suspicious CAN traffic using predefined rules and traffic constraints.
- **Machine-learning-based detection:** Uses a trained model to identify anomalous or potentially malicious CAN traffic.

Both approaches will be evaluated under comparable resource constraints, with a focus on detection performance and suitability for embedded systems.

## Research Objectives

The comparison will focus on:

- Detection accuracy and false-positive rate
- Processing time per CAN frame
- CPU utilization
- Memory consumption
- Behavior under high CAN bus traffic
- Deployment constraints on the STM32MP135F platform

The objective is to understand the trade-offs between deterministic rule-based detection and machine-learning-based anomaly detection on resource-constrained embedded hardware.

This repository holds the underlying distro: a hardened embedded Linux platform, built from scratch with Yocto (kas + custom BSP/distro layers), that hosts the detection stack described above.

## Layout

| Path | What it is |
|---|---|
| `canary/` | Fetched by kas — bitbake, oe-core, meta-st-stm32mp, meta-openembedded. |
| `meta-canary-bsp/` | Board customizations: U-Boot splash, bootstage instrumentation. |
| `meta-canary-distro/` | Distro policy — `canary.conf`: init system, hardening flags. |
| `meta-canary-project/` | Application layer — `canary-ui` today, the NIDS engine next. |
| `docker/` | Reproducible build environment. |
| `KAS/canary.yml` | Single source of truth for the whole build. |

## Why a custom distro

## Reproducible Security Evaluation

The distribution is designed to support controlled comparisons between hardened and unhardened system configurations. Both variants are generated from a common source tree to improve reproducibility and isolate the impact of security-related changes.

## Boot Performance Analysis

Boot-time performance is measured using U-Boot Bootstage instrumentation and serial-console tracing with `grabserial`. These measurements establish a baseline for identifying bottlenecks and evaluating the impact of system-level optimizations.

## Building

```bash
docker build -t canary-builder docker/
docker run -it -v "$(pwd):/home/builder/project" -e KAS_WORK_DIR=/home/builder/project/canary canary-builder bash
kas build KAS/canary.yml
```

Flashable images land in `canary/build/tmp-glibc/deploy/images/stm32mp1/`.

## Status

- [x] Boots to a login prompt on STM32MP135F-DK
- [x] U-Boot bootstage instrumentation, baseline timing captured
- [x] Custom U-Boot splash screen
- [ ] CAN NIDS engine (rule-based + learned model comparison) -- in progress
- [ ] Boot-time optimization pass 
- [ ] Hardening pass and before/after measurement

## Related

- [`canary-ui`](https://github.com/bilal-marghich/canary-ui) — LVGL diagnostic dashboard running on this distro

## License

MIT
