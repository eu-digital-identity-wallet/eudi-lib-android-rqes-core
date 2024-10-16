/*
 * Copyright (c) 2024 European Commission
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.europa.ec.eudi.rqes.core

/**
 * Interface for the Signature Creation Application service.
 *
 * This service provides the functionality to calculate the hash of a document and to embed a signature into a document.
 *
 * @see DocumentHashCalculator
 * @see DocumentSignatureEmbedder
 */
interface SCAService : DocumentHashCalculator, DocumentSignatureEmbedder