# OSS-Middleware (Scrape Version)

#### Note: _This repository is a scrape version of the original code base and just for demo; the original one is private due to an NDA._

## Introduction

The "**OSS-Middleware**" project is part of a larger solution named: "**BSS/OSS** (Business/Operation Support System)" for "**TIC (Telecom Infrastructure Company)**- _The largest Network Infrastructure Provider in the Middle-East._"

The main job of the "**OSS-Middleware**" sub-system is providing a standard, secure, scalable, high-throughput, async, easy-to-use RESTful APIs for accessing/updating/provisioning Network Infrastructure objects/connections of:
- **Data**
- **Switch**
- **Transmit** _Network Types_ in an asynchronous (Promise) manner.

It supports multiple _Protocols_ including:
- **CORBA**
- **ISMN**
- **MML**
- **REST**
- **SNMP**
- **SOAP**
- **RPC**, etc.

It also can connect to multiple **Network Management Systems (NMSes)** provided by various _Vendors_, including:
- **Huawei**
- **Cisco**
- **Fana**
- **Fiberhome**
- **Ewsd**
- **NCE**
- **Neax**
- **ZTE**, etc.

## Project Time-line & Staff
The project has been coded in 3 phases which are as following:
- **1st Phase:** from _December 2020_ to _May 2021_, which contains all the major functionalities and modules + 4 main Adapters
- **2nd Phase:** from _June 2021_ to _November 2021_, which focused on extending functionalities and mainly developing various Adapters.
- **3rd Phase:** from _December 2021_ to _June 2022_, which the provisioning features were developed.

and delivered in a fast-paced time-frame on _Kanban_ methodology.

The Tech Team involved in the project consist of:
- 1 Application Architect/Lead Developer/Tech Lead/ Team Leader
- 2 Senior Back-end Developer (1 of them also as a major Code Reviewer)
- 2 Mid-Level Back-end Developer
- 3 Junior Back-end Developer
- 1 Lead DevOps Engineer
- 1 Mid-Level Front-end Developer
- 1 Product Owner
- 1 Scrum Master
- 1 Technical Writer
- 1 UI/UX Designer

## Project Structure
The project is structured as a Micro-Service Modular system.

Its structure consist of:
- **1 Core Module:** Which is upper-interface and the NBI of the sub-system and contains the core functionality of the sub-system.
- **1 Common Module:** which contains the common objects and behaviors that other modules use as shared.
- **Several Adapters:** which named preceding with "Adapter" following by the vendor name and the network type.
- **1 Message Broker Module:** which is used as a discovery module and configurer, and also the message broker driver module.

All modules act independently, in a state-less manner.

## Project Version-Control
The project version-control is on Git and follows Semi-Git-Flow.

It contains three major, protected branches:
- **Master:** which is the equivalent to the "Production" branch. The hot-fixes are merged into it after code-review.
- **Development:** which is the development branch that all features and/or bug-fixes are merged into, after code-review.
- **Debug:** which is the mirror of the actual "Production" in the production environment, for debugging purposes.
